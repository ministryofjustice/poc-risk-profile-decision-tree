package org.moj.cattool.storagelayer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.moj.cattool.datasource.DataSource;
import org.moj.cattool.exceptions.MalformedHeader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class LocalFileSystem extends StorageBase{

    private String path;

    public LocalFileSystem(String path){
        this.path = path;
    }

    public HashMap<String, DataSource> readFile(Class datasource) throws IOException {
        HashMap<String, DataSource> toReturn = new HashMap<>();
        File csvFile = new File(this.path);
        CsvMapper mapper = new CsvMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        CsvSchema schema = mapper.schemaFor(datasource).withHeader().withColumnReordering(true);
        MappingIterator<DataSource> it = mapper.readerFor(datasource).with(schema).readValues(csvFile);

        while (it.hasNextValue()) {
            DataSource value = it.nextValue();
            toReturn.put(value.getNomisID(), value);
        }
        return toReturn;
    }

    public HashMap<String, DataSource> readSparseFile(Class datasource) throws IOException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, MalformedHeader {

        HashMap<String, DataSource> toReturn = new HashMap<>();
        File csvFile = new File(this.path);
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        MappingIterator<String[]> iterator = csvMapper.readerFor(String[].class).readValues(csvFile);
        boolean validateHeader = true;
        while (iterator.hasNext()) {
            String[] row = iterator.next();
            DataSource obj = (DataSource) datasource.getDeclaredConstructor().newInstance();
            if( validateHeader ) {
                obj.validateHeader(row);
                validateHeader = false;
                continue;
            }
            obj.setValues(row);
            toReturn.put(obj.getNomisID(), obj);
        }

        return toReturn;
    }
}
