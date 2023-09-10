// Generated by Cap'n Proto compiler, DO NOT EDIT
// source: crypto.capnp

package tools.elide.crypto;

public final class Crypto {
  public enum HashAlgorithm {
    IDENTITY,
    MD5,
    SHA1,
    SHA2,
    SHA256,
    SHA384,
    SHA512,
    SHA3224,
    SHA3256,
    SHA3512,
    _NOT_IN_SCHEMA,
  }


public static final class Schemas {
public static final org.capnproto.SegmentReader b_c44010948997e2a9 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u00a9\u00e2\u0097\u0089\u0094\u0010\u0040\u00c4" +
   "\u001a\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u001b\u003e\u0054\u00f0\u00b9\u00bc\u00ff\u008a" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u0042\u0001\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0021\u0000\u0000\u0000\u00f7\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0065\u006c\u0069\u0064\u0065\u002f\u0063\u0072" +
   "\u0079\u0070\u0074\u006f\u002f\u0063\u0072\u0079" +
   "\u0070\u0074\u006f\u002e\u0063\u0061\u0070\u006e" +
   "\u0070\u003a\u0048\u0061\u0073\u0068\u0041\u006c" +
   "\u0067\u006f\u0072\u0069\u0074\u0068\u006d\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0028\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0071\u0000\u0000\u0000\u004a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006d\u0000\u0000\u0000\"\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0065\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0003\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u005d\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0055\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u004d\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0006\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0045\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0007\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u003d\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0008\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0035\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0009\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u002d\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0069\u0064\u0065\u006e\u0074\u0069\u0074\u0079" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006d\u0064\u0035\u0000\u0000\u0000\u0000\u0000" +
   "\u0073\u0068\u0061\u0031\u0000\u0000\u0000\u0000" +
   "\u0073\u0068\u0061\u0032\u0000\u0000\u0000\u0000" +
   "\u0073\u0068\u0061\u0032\u0035\u0036\u0000\u0000" +
   "\u0073\u0068\u0061\u0033\u0038\u0034\u0000\u0000" +
   "\u0073\u0068\u0061\u0035\u0031\u0032\u0000\u0000" +
   "\u0073\u0068\u0061\u0033\u0032\u0032\u0034\u0000" +
   "\u0073\u0068\u0061\u0033\u0032\u0035\u0036\u0000" +
   "\u0073\u0068\u0061\u0033\u0035\u0031\u0032\u0000" + "");
}
}
