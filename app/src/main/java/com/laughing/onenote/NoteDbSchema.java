package com.laughing.onenote;

public class NoteDbSchema {
    public static  final  class NoteTable{
        public  static  final  String NAME="notes";

        public  static  final  class Cols{
            public static  final String UUID="uuid";
            public static  final String TITLE="title";
            public static  final String DATE="date";
            public static  final String LOCATION="location";
            public static  final String TEXT="text";
            public static  final String TYPE="type";


        }

    }
}
