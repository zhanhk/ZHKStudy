package com.zhk.protobuf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        LogicalDataModelProtos.Envelope.Builder builder = LogicalDataModelProtos.Envelope.newBuilder();
        String appkey = builder.getAppKey();
    }
   
    
}
