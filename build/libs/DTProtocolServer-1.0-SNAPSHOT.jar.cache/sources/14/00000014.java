package protocol.p001io;

import annotation.PacketData;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import org.jetbrains.annotations.NotNull;
import protocol.Auth;
import protocol.DTPacket;

/* compiled from: ProtocolOutputStream.kt */
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0018\n��\n\u0002\u0010\u0005\n��\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n��\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n��\n\u0002\u0010\u0014\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0015\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\n\n��\n\u0002\u0010\u0017\n��\n\u0002\u0010\u000e\n��\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00062\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001dJ\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020%J\u000e\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020*J\u000e\u0010+\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020,J\u000e\u0010-\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020.J\u000e\u0010/\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u000200J\u000e\u00101\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u000202J\u000e\u00103\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u000204J\u0006\u00105\u001a\u00020\u0006J\u000e\u00106\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u000207J\u000e\u00108\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u000209J\u000e\u0010:\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020;¨\u0006<"}, d2 = {"Lprotocol/io/ProtocolOutputStream;", "Ljava/io/DataOutputStream;", "s", "Ljava/io/OutputStream;", "(Ljava/io/OutputStream;)V", "beginAuth", "", "beginFlags", "beginMessage", "id", "Lprotocol/DTPacket$PacketID;", "beginObject", "endFlags", "endMessage", "endObject", "writeAuth", "auth", "Lprotocol/Auth;", "writeBooleanField", "value", "", "writeBooleans", "", "writeByteField", "", "writeBytes", "", "writeDataObject", "data", "", "", "writeDatetimeField", "date", "Ljava/util/Date;", "writeDoubleField", "", "writeDoubles", "", "writeFlag", "flag", "Lprotocol/DTPacket$PacketFlag;", "writeFloatField", "", "writeFloats", "", "writeIntField", "", "writeInts", "", "writeLongField", "", "writeLongs", "", "writeNull", "writeShortField", "", "writeShorts", "", "writeStringField", "", "DTProtocolServer"})
/* renamed from: protocol.io.ProtocolOutputStream */
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/io/ProtocolOutputStream.class */
public final class ProtocolOutputStream extends DataOutputStream {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProtocolOutputStream(@NotNull OutputStream s) {
        super(s);
        Intrinsics.checkNotNullParameter(s, "s");
    }

    public final void writeNull() {
        writeByte(1);
    }

    public final void writeBooleanField(boolean value) {
        writeByte(16);
        writeBoolean(value);
    }

    public final void writeByteField(byte value) {
        writeByte(17);
        writeByte(value);
    }

    public final void writeShortField(short value) {
        writeByte(18);
        writeShort(value);
    }

    public final void writeIntField(int value) {
        writeByte(19);
        writeInt(value);
    }

    public final void writeLongField(long value) {
        writeByte(20);
        writeLong(value);
    }

    public final void writeFloatField(float value) {
        writeByte(20);
        writeFloat(value);
    }

    public final void writeDoubleField(double value) {
        writeByte(22);
        writeDouble(value);
    }

    public final void writeDatetimeField(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        writeByte(23);
        writeLong(date.getTime());
    }

    public final void writeStringField(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(32);
        writeUTF(value);
    }

    public final void writeBooleans(@NotNull boolean[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(33);
        writeShort(value.length);
        for (boolean element$iv : value) {
            writeBoolean(element$iv);
        }
    }

    public final void writeBytes(@NotNull byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(34);
        writeInt(value.length);
        write(value);
    }

    public final void writeShorts(@NotNull short[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(35);
        writeShort(value.length);
        for (short element$iv : value) {
            writeShort(element$iv);
        }
    }

    public final void writeInts(@NotNull int[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(36);
        writeShort(value.length);
        for (int element$iv : value) {
            writeInt(element$iv);
        }
    }

    public final void writeLongs(@NotNull long[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(37);
        writeShort(value.length);
        for (long element$iv : value) {
            writeLong(element$iv);
        }
    }

    public final void writeFloats(@NotNull float[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(38);
        writeShort(value.length);
        for (float element$iv : value) {
            writeFloat(element$iv);
        }
    }

    public final void writeDoubles(@NotNull double[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        writeByte(39);
        writeShort(value.length);
        for (double element$iv : value) {
            writeDouble(element$iv);
        }
    }

    public final void writeDataObject(@NotNull List<? extends Object> list) {
        List list2;
        Object obj;
        Intrinsics.checkNotNullParameter(list, "data");
        writeByte(list.size());
        beginObject();
        for (Object field : list) {
            if (field == null) {
                writeNull();
            } else if (field instanceof Boolean) {
                writeBooleanField(((Boolean) field).booleanValue());
            } else if (field instanceof Byte) {
                writeByteField(((Number) field).byteValue());
            } else if (field instanceof Short) {
                writeShortField(((Number) field).shortValue());
            } else if (field instanceof Integer) {
                writeIntField(((Number) field).intValue());
            } else if (field instanceof Long) {
                writeLongField(((Number) field).longValue());
            } else if (field instanceof Float) {
                writeFloatField(((Number) field).floatValue());
            } else if (field instanceof Double) {
                writeDoubleField(((Number) field).doubleValue());
            } else if (field instanceof Date) {
                writeDatetimeField((Date) field);
            } else if (field instanceof String) {
                writeStringField((String) field);
            } else if (field instanceof boolean[]) {
                writeBooleans((boolean[]) field);
            } else if (field instanceof byte[]) {
                writeBytes((byte[]) field);
            } else if (field instanceof short[]) {
                writeShorts((short[]) field);
            } else if (field instanceof int[]) {
                writeInts((int[]) field);
            } else if (field instanceof long[]) {
                writeLongs((long[]) field);
            } else if (field instanceof float[]) {
                writeFloats((float[]) field);
            } else if (field instanceof double[]) {
                writeDoubles((double[]) field);
            } else {
                if (field instanceof List) {
                    list2 = (List) field;
                } else if (field instanceof Object[]) {
                    list2 = ArraysKt.toList((Object[]) field);
                } else if (field instanceof Set) {
                    list2 = CollectionsKt.toList((Iterable) field);
                } else {
                    KAnnotatedElement $this$hasAnnotation$iv = Reflection.getOrCreateKotlinClass(field.getClass());
                    Iterable $this$firstOrNull$iv$iv$iv = $this$hasAnnotation$iv.getAnnotations();
                    Iterator it = $this$firstOrNull$iv$iv$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv$iv$iv = it.next();
                            Annotation it$iv$iv = (Annotation) element$iv$iv$iv;
                            if (it$iv$iv instanceof PacketData) {
                                obj = element$iv$iv$iv;
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    if (!(((PacketData) obj) != null)) {
                        System.out.println((Object) ("[ProtocolOutputStream] Field type " + Reflection.getOrCreateKotlinClass(field.getClass()).getSimpleName() + " is unsupported."));
                    } else {
                        Iterable $this$filter$iv = KClasses.getDeclaredMemberProperties(Reflection.getOrCreateKotlinClass(field.getClass()));
                        Collection destination$iv$iv = new ArrayList();
                        for (Object element$iv$iv : $this$filter$iv) {
                            KProperty1 property = (KProperty1) element$iv$iv;
                            if (property.getVisibility() == KVisibility.PUBLIC) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                        }
                        Iterable $this$map$iv = (List) destination$iv$iv;
                        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            KProperty1 property2 = (KProperty1) item$iv$iv;
                            destination$iv$iv2.add(property2.getGetter().call(new Object[]{field}));
                        }
                        list2 = (List) destination$iv$iv2;
                    }
                }
                List dataObject = list2;
                writeByte(48);
                writeDataObject(dataObject);
            }
        }
        endObject();
    }

    public final void beginMessage(@NotNull DTPacket.PacketID id) {
        Intrinsics.checkNotNullParameter(id, "id");
        writeShort(id.getValue());
    }

    public final void beginFlags() {
        writeByte(10);
    }

    public final void writeFlag(@NotNull DTPacket.PacketFlag flag) {
        Intrinsics.checkNotNullParameter(flag, "flag");
        writeByte(flag.getValue());
    }

    public final void endFlags() {
        writeByte(15);
    }

    public final void beginAuth() {
        writeByte(26);
    }

    public final void writeAuth(@NotNull Auth auth) {
        Intrinsics.checkNotNullParameter(auth, "auth");
        write(auth.getSignature());
        Auth.PacketAuth $this$writeAuth_u24lambda_u2d8 = auth.getData();
        writeLong($this$writeAuth_u24lambda_u2d8.getUserId());
        writeByte($this$writeAuth_u24lambda_u2d8.getRole());
        writeLong($this$writeAuth_u24lambda_u2d8.getAuthTime());
    }

    public final void beginObject() {
        writeByte(DTPacket.MagicNumber.BEGIN_OBJECT);
    }

    public final void endObject() {
        writeByte(DTPacket.MagicNumber.END_OBJECT);
    }

    public final void endMessage() {
        writeByte(0);
    }
}