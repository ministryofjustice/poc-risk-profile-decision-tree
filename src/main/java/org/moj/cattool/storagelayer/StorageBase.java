package org.moj.cattool.storagelayer;


import org.moj.cattool.datasource.DataSource;

import java.io.IOException;
import java.util.HashMap;

abstract class StorageBase {

    public abstract HashMap<String, DataSource> readFile(Class in) throws IOException;

}
