package protocol.p001io;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import protocol.Auth;
import protocol.DTPacket;
import protocol.error.TokenMismatchException;
import protocol.error.UnknownPacketException;

/* compiled from: ProtocolInputStream.kt */
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n��\n\u0002\u0010\u0013\n��\n\u0002\u0010\u0007\n��\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0016\n��\n\u0002\u0010\n\n��\n\u0002\u0010\u0017\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010\u001b\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001dJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u000eJ\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\n 6*\u0004\u0018\u00010505J\u0006\u00107\u001a\u000208J\b\u00109\u001a\u0004\u0018\u00010:J\u001e\u0010;\u001a\u00020<2\u0006\u0010\r\u001a\u00020\u000eø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b=\u0010>\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006?"}, d2 = {"Lprotocol/io/ProtocolInputStream;", "Ljava/io/DataInputStream;", "s", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "beginAuth", "", "beginFlags", "beginMessage", "Lprotocol/DTPacket$PacketID;", "beginObject", "byteAfter", "", "after", "", "bytesAfter", "", "count", "endFlags", "endMessage", "endObject", "isNextNull", "", "nextBoolean", "nextBooleans", "", "nextByte", "nextBytes", "nextDataObject", "", "", "nextDatetime", "Ljava/util/Date;", "nextDouble", "", "nextDoubles", "", "nextFloat", "", "nextFloats", "", "nextInt", "nextInts", "", "nextLong", "", "nextLongs", "", "nextShort", "", "nextShorts", "", "nextString", "", "kotlin.jvm.PlatformType", "readAuth", "Lprotocol/Auth;", "readFlag", "Lprotocol/DTPacket$PacketFlag;", "uByteAfter", "Lkotlin/UByte;", "uByteAfter-Wa3L5BU", "(I)B", "DTProtocolServer"})
/* renamed from: protocol.io.ProtocolInputStream */
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/io/ProtocolInputStream.class */
public final class ProtocolInputStream extends DataInputStream {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProtocolInputStream(@NotNull InputStream s) {
        super(s);
        Intrinsics.checkNotNullParameter(s, "s");
    }

    public final boolean isNextNull() {
        return readUnsignedByte() == 1;
    }

    public final boolean nextBoolean() {
        return readBoolean();
    }

    public final int nextByte() {
        return readUnsignedByte();
    }

    public final short nextShort() {
        return readShort();
    }

    public final int nextInt() {
        return readInt();
    }

    public final long nextLong() {
        return readLong();
    }

    public final float nextFloat() {
        return readFloat();
    }

    public final double nextDouble() {
        return readDouble();
    }

    @NotNull
    public final Date nextDatetime() {
        return new Date(readLong());
    }

    public final String nextString() {
        return readUTF();
    }

    @NotNull
    public final boolean[] nextBooleans() {
        int readUnsignedShort = readUnsignedShort();
        boolean[] zArr = new boolean[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            zArr[i] = readBoolean();
        }
        return zArr;
    }

    @NotNull
    public final byte[] nextBytes() {
        byte[] $this$nextBytes_u24lambda_u2d0 = new byte[readInt()];
        read($this$nextBytes_u24lambda_u2d0);
        return $this$nextBytes_u24lambda_u2d0;
    }

    @NotNull
    public final short[] nextShorts() {
        int readUnsignedShort = readUnsignedShort();
        short[] sArr = new short[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            sArr[i] = (short) readUnsignedShort();
        }
        return sArr;
    }

    @NotNull
    public final int[] nextInts() {
        int readUnsignedShort = readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            iArr[i] = readInt();
        }
        return iArr;
    }

    @NotNull
    public final long[] nextLongs() {
        int readUnsignedShort = readUnsignedShort();
        long[] jArr = new long[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            jArr[i] = readLong();
        }
        return jArr;
    }

    @NotNull
    public final float[] nextFloats() {
        int readUnsignedShort = readUnsignedShort();
        float[] fArr = new float[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            fArr[i] = readFloat();
        }
        return fArr;
    }

    @NotNull
    public final double[] nextDoubles() {
        int readUnsignedShort = readUnsignedShort();
        double[] dArr = new double[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            dArr[i] = readDouble();
        }
        return dArr;
    }

    @NotNull
    public final List<Object> nextDataObject() {
        Object obj;
        int size = readUnsignedByte();
        beginObject();
        Object[] values = new Object[size];
        for (int i = 0; i < size; i++) {
            int i2 = i;
            int typeId = readUnsignedByte();
            switch (typeId) {
                case DTPacket.MagicNumber.FIELD_NULL /* 1 */:
                    obj = null;
                    break;
                case DTPacket.MagicNumber.FIELD_BOOLEAN /* 16 */:
                    obj = Boolean.valueOf(nextBoolean());
                    break;
                case DTPacket.MagicNumber.FIELD_BYTE /* 17 */:
                    obj = Integer.valueOf(nextByte());
                    break;
                case DTPacket.MagicNumber.FIELD_SHORT /* 18 */:
                    obj = Short.valueOf(nextShort());
                    break;
                case DTPacket.MagicNumber.FIELD_INT /* 19 */:
                    obj = Integer.valueOf(nextInt());
                    break;
                case DTPacket.MagicNumber.FIELD_LONG /* 20 */:
                    obj = Long.valueOf(nextLong());
                    break;
                case DTPacket.MagicNumber.FIELD_FLOAT /* 21 */:
                    obj = Float.valueOf(nextFloat());
                    break;
                case DTPacket.MagicNumber.FIELD_DOUBLE /* 22 */:
                    obj = Double.valueOf(nextDouble());
                    break;
                case DTPacket.MagicNumber.FIELD_DATETIME /* 23 */:
                    obj = nextDatetime();
                    break;
                case DTPacket.MagicNumber.FIELD_STRING /* 32 */:
                    obj = nextString();
                    break;
                case DTPacket.MagicNumber.FIELD_BOOLEAN_ARRAY /* 33 */:
                    obj = nextBooleans();
                    break;
                case DTPacket.MagicNumber.FIELD_BYTE_ARRAY /* 34 */:
                    obj = nextBytes();
                    break;
                case DTPacket.MagicNumber.FIELD_SHORT_ARRAY /* 35 */:
                    obj = nextShorts();
                    break;
                case DTPacket.MagicNumber.FIELD_INT_ARRAY /* 36 */:
                    obj = nextInts();
                    break;
                case DTPacket.MagicNumber.FIELD_LONG_ARRAY /* 37 */:
                    obj = nextLongs();
                    break;
                case DTPacket.MagicNumber.FIELD_FLOAT_ARRAY /* 38 */:
                    obj = nextFloats();
                    break;
                case DTPacket.MagicNumber.FIELD_DOUBLE_ARRAY /* 39 */:
                    obj = nextDoubles();
                    break;
                case DTPacket.MagicNumber.FIELD_OBJECT /* 48 */:
                    obj = nextDataObject();
                    break;
                default:
                    System.out.println((Object) ("[ProtocolInputStream] Failed to determine type of the field with type id " + typeId));
                    obj = null;
                    break;
            }
            values[i2] = obj;
        }
        endObject();
        return ArraysKt.toList(values);
    }

    @NotNull
    public final DTPacket.PacketID beginMessage() {
        DTPacket.PacketID packetID;
        int id = readUnsignedShort();
        DTPacket.PacketID[] values = DTPacket.PacketID.values();
        int i = 0;
        int length = values.length;
        while (true) {
            if (i >= length) {
                packetID = null;
                break;
            }
            DTPacket.PacketID it = values[i];
            if (it.getValue() == id) {
                packetID = it;
                break;
            }
            i++;
        }
        if (packetID == null) {
            throw new UnknownPacketException();
        }
        return packetID;
    }

    public final void beginFlags() {
        if (readUnsignedByte() != 10) {
            throw new TokenMismatchException();
        }
    }

    @Nullable
    public final DTPacket.PacketFlag readFlag() {
        DTPacket.PacketFlag[] values;
        int id = readUnsignedByte();
        for (DTPacket.PacketFlag it : DTPacket.PacketFlag.values()) {
            if (it.getValue() == id) {
                return it;
            }
        }
        return null;
    }

    public final void endFlags() {
        if (readUnsignedByte() != 15) {
            throw new TokenMismatchException();
        }
    }

    public final void beginAuth() {
        if (readUnsignedByte() != 26) {
            throw new TokenMismatchException();
        }
    }

    @NotNull
    public final Auth readAuth() {
        beginAuth();
        byte[] readNBytes = readNBytes(32);
        Intrinsics.checkNotNullExpressionValue(readNBytes, "readNBytes(32)");
        return new Auth(readNBytes, new Auth.PacketAuth(readLong(), readByte(), readLong()));
    }

    public final void beginObject() {
        if (readUnsignedByte() != 170) {
            throw new TokenMismatchException();
        }
    }

    public final void endObject() {
        if (readUnsignedByte() != 255) {
            throw new TokenMismatchException();
        }
    }

    public final void endMessage() {
        if (readUnsignedByte() != 0) {
            throw new TokenMismatchException();
        }
    }

    @NotNull
    public final byte[] bytesAfter(int after, int count) {
        mark(after + count);
        skip(after);
        byte[] readNBytes = readNBytes(count);
        reset();
        Intrinsics.checkNotNullExpressionValue(readNBytes, "readNBytes(count).apply { reset() }");
        return readNBytes;
    }

    public final byte byteAfter(int after) {
        return bytesAfter(after, 1)[0];
    }

    /* renamed from: uByteAfter-Wa3L5BU  reason: not valid java name */
    public final byte m8uByteAfterWa3L5BU(int after) {
        return UByte.constructor-impl(byteAfter(after));
    }
}