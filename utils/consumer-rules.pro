# 删除代码中Log相关的代码
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-assumenosideeffects class com.hy.utils.Logger {
      public static void v(...);
      public static void i(...);
      public static void w(...);
      public static void d(...);
      public static void e(...);
      public static void wtf(...);
      public static void printJson(...);
      public static void log(...);
}