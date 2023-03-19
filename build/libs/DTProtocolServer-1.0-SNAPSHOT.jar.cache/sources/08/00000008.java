package protocol;

import annotation.PacketData;
import annotation.ProtocolException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.full.KClasses;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import protocol.error.MalformedPacketException;
import protocol.error.PacketConvertingException;
import protocol.error.UnknownPacketException;
import protocol.p001io.ProtocolOutputStream;
import protocol.packet.P0x1ServerInfo;
import protocol.packet.P0x2AckServerInfo;
import protocol.packet.P0x3Login;
import protocol.packet.P0x4Register;
import protocol.packet.P0x5AckAuth;
import protocol.packet.P0x6Profile;
import protocol.packet.P0x7AckProfile;

/* compiled from: DTPacket.kt */
@Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010!\n��\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018�� )2\u00020\u0001:\u0005)*+,-B5\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\tB\u001d\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000bB\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\fB-\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\rB\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u000eB\u001f\b\u0016\u0012\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000fB\u0007\b\u0016¢\u0006\u0002\u0010\u0010B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0014J\u000e\u0010#\u001a\u00020��2\u0006\u0010$\u001a\u00020\u0004J\u0006\u0010%\u001a\u00020&J\u000e\u0010\u0017\u001a\u00020��2\u0006\u0010\u0005\u001a\u00020\u0006J\u0014\u0010\u001a\u001a\u00020��2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u000e\u0010!\u001a\u00020��2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010'\u001a\u00020(H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\fR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u000eR\u0012\u0010\u001b\u001a\u00020\u001cX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006."}, d2 = {"Lprotocol/DTPacket;", "", "flags", "", "Lprotocol/DTPacket$PacketFlag;", "auth", "Lprotocol/Auth;", "data", "", "(Ljava/util/List;Lprotocol/Auth;[Ljava/lang/Object;)V", "(Ljava/util/List;Lprotocol/Auth;)V", "(Lprotocol/Auth;[Ljava/lang/Object;)V", "(Lprotocol/Auth;)V", "(Ljava/util/List;[Ljava/lang/Object;)V", "(Ljava/util/List;)V", "([Ljava/lang/Object;)V", "()V", "", "timestamp", "", "(Ljava/util/List;JLprotocol/Auth;Ljava/util/List;)V", "getAuth", "()Lprotocol/Auth;", "setAuth", "getFlags", "()Ljava/util/List;", "setFlags", "id", "Lprotocol/DTPacket$PacketID;", "getId", "()Lprotocol/DTPacket$PacketID;", "getTimestamp", "()J", "setTimestamp", "(J)V", "addFlag", "flag", "serialize", "", "toString", "", "Companion", "MagicNumber", "PacketFlag", "PacketID", "Role", "DTProtocolServer"})
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket.class */
public abstract class DTPacket {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private List<PacketFlag> flags;
    private long timestamp;
    @Nullable
    private Auth auth;
    @NotNull
    private final List<Object> data;

    @NotNull
    public abstract PacketID getId();

    @JvmStatic
    @NotNull
    public static final DTPacket deserialize(@NotNull byte[] packet) {
        return Companion.deserialize(packet);
    }

    public DTPacket(@NotNull List<PacketFlag> list, long timestamp, @Nullable Auth auth, @NotNull List<? extends Object> list2) {
        Intrinsics.checkNotNullParameter(list, "flags");
        Intrinsics.checkNotNullParameter(list2, "data");
        this.flags = list;
        this.timestamp = timestamp;
        this.auth = auth;
        this.data = list2;
    }

    public /* synthetic */ DTPacket(List list, long j, Auth auth, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, j, (i & 4) != 0 ? null : auth, (i & 8) != 0 ? CollectionsKt.emptyList() : list2);
    }

    @NotNull
    public final List<PacketFlag> getFlags() {
        return this.flags;
    }

    public final void setFlags(@NotNull List<PacketFlag> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.flags = list;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    @Nullable
    public final Auth getAuth() {
        return this.auth;
    }

    public final void setAuth(@Nullable Auth auth) {
        this.auth = auth;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull List<? extends PacketFlag> list, @NotNull Auth auth, @NotNull Object... data) {
        this(CollectionsKt.toMutableList(list), System.currentTimeMillis(), auth, ArraysKt.toList(data));
        Intrinsics.checkNotNullParameter(list, "flags");
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull List<? extends PacketFlag> list, @NotNull Auth auth) {
        this(CollectionsKt.toMutableList(list), System.currentTimeMillis(), auth, CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(list, "flags");
        Intrinsics.checkNotNullParameter(auth, "auth");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull Auth auth, @NotNull Object... data) {
        this(new ArrayList(), System.currentTimeMillis(), auth, ArraysKt.toList(data));
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull Auth auth) {
        this(new ArrayList(), System.currentTimeMillis(), auth, CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(auth, "auth");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull List<? extends PacketFlag> list, @NotNull Object... data) {
        this(CollectionsKt.toMutableList(list), System.currentTimeMillis(), null, ArraysKt.toList(data));
        Intrinsics.checkNotNullParameter(list, "flags");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull List<? extends PacketFlag> list) {
        this(CollectionsKt.toMutableList(list), System.currentTimeMillis(), null, CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(list, "flags");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DTPacket(@NotNull Object... data) {
        this(new ArrayList(), System.currentTimeMillis(), null, ArraysKt.toList(data));
        Intrinsics.checkNotNullParameter(data, "data");
    }

    public DTPacket() {
        this(new ArrayList(), System.currentTimeMillis(), null, CollectionsKt.emptyList());
    }

    /* compiled from: DTPacket.kt */
    @Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��¨\u0006\u001c"}, d2 = {"Lprotocol/DTPacket$MagicNumber;", "", "()V", "AUTHENTICATED", "", "BEGIN_FLAGS", "BEGIN_OBJECT", "END_FLAGS", "END_OBJECT", "FIELD_BOOLEAN", "FIELD_BOOLEAN_ARRAY", "FIELD_BYTE", "FIELD_BYTE_ARRAY", "FIELD_DATETIME", "FIELD_DOUBLE", "FIELD_DOUBLE_ARRAY", "FIELD_FLOAT", "FIELD_FLOAT_ARRAY", "FIELD_INT", "FIELD_INT_ARRAY", "FIELD_LONG", "FIELD_LONG_ARRAY", "FIELD_NULL", "FIELD_OBJECT", "FIELD_SHORT", "FIELD_SHORT_ARRAY", "FIELD_STRING", "MESSAGE_ENDING", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket$MagicNumber.class */
    public static final class MagicNumber {
        @NotNull
        public static final MagicNumber INSTANCE = new MagicNumber();
        public static final int BEGIN_FLAGS = 10;
        public static final int END_FLAGS = 15;
        public static final int FIELD_NULL = 1;
        public static final int FIELD_BOOLEAN = 16;
        public static final int FIELD_BYTE = 17;
        public static final int FIELD_SHORT = 18;
        public static final int FIELD_INT = 19;
        public static final int FIELD_LONG = 20;
        public static final int FIELD_FLOAT = 21;
        public static final int FIELD_DOUBLE = 22;
        public static final int FIELD_DATETIME = 23;
        public static final int FIELD_STRING = 32;
        public static final int FIELD_BOOLEAN_ARRAY = 33;
        public static final int FIELD_BYTE_ARRAY = 34;
        public static final int FIELD_SHORT_ARRAY = 35;
        public static final int FIELD_INT_ARRAY = 36;
        public static final int FIELD_LONG_ARRAY = 37;
        public static final int FIELD_FLOAT_ARRAY = 38;
        public static final int FIELD_DOUBLE_ARRAY = 39;
        public static final int FIELD_OBJECT = 48;
        public static final int BEGIN_OBJECT = 170;
        public static final int END_OBJECT = 255;
        public static final int AUTHENTICATED = 26;
        public static final int MESSAGE_ENDING = 0;

        private MagicNumber() {
        }
    }

    /* compiled from: DTPacket.kt */
    @Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\n\n��\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lprotocol/DTPacket$PacketID;", "", "value", "", "clazz", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;ISLkotlin/reflect/KClass;)V", "getClazz", "()Lkotlin/reflect/KClass;", "getValue", "()S", "SERVER_INFO", "ACK_SERVER_INFO", "LOGIN", "REGISTER", "ACK_AUTH", "PROFILE", "ACK_PROFILE", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket$PacketID.class */
    public enum PacketID {
        SERVER_INFO(1, Reflection.getOrCreateKotlinClass(P0x1ServerInfo.class)),
        ACK_SERVER_INFO(2, Reflection.getOrCreateKotlinClass(P0x2AckServerInfo.class)),
        LOGIN(3, Reflection.getOrCreateKotlinClass(P0x3Login.class)),
        REGISTER(4, Reflection.getOrCreateKotlinClass(P0x4Register.class)),
        ACK_AUTH(5, Reflection.getOrCreateKotlinClass(P0x5AckAuth.class)),
        PROFILE(6, Reflection.getOrCreateKotlinClass(P0x6Profile.class)),
        ACK_PROFILE(7, Reflection.getOrCreateKotlinClass(P0x7AckProfile.class));
        
        private final short value;
        @NotNull
        private final KClass<?> clazz;

        PacketID(short value, KClass clazz) {
            this.value = value;
            this.clazz = clazz;
        }

        public final short getValue() {
            return this.value;
        }

        @NotNull
        public final KClass<?> getClazz() {
            return this.clazz;
        }
    }

    /* compiled from: DTPacket.kt */
    @Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lprotocol/DTPacket$PacketFlag;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "NO_ACK", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket$PacketFlag.class */
    public enum PacketFlag {
        NO_ACK(1);
        
        private final int value;

        PacketFlag(int value) {
            this.value = value;
        }

        public final int getValue() {
            return this.value;
        }
    }

    /* compiled from: DTPacket.kt */
    @Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u0005\n\u0002\b\u0007\b\u0086\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lprotocol/DTPacket$Role;", "", "value", "", "(Ljava/lang/String;IB)V", "getValue", "()B", "USER", "ADMIN", "SUPER_ADMIN", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket$Role.class */
    public enum Role {
        USER((byte) 1),
        ADMIN((byte) 2),
        SUPER_ADMIN((byte) 3);
        
        private final byte value;

        Role(byte value) {
            this.value = value;
        }

        public final byte getValue() {
            return this.value;
        }
    }

    @NotNull
    /* renamed from: setTimestamp  reason: collision with other method in class */
    public final DTPacket m1setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @NotNull
    /* renamed from: setFlags  reason: collision with other method in class */
    public final DTPacket m2setFlags(@NotNull List<? extends PacketFlag> list) {
        Intrinsics.checkNotNullParameter(list, "flags");
        this.flags = CollectionsKt.toMutableList(list);
        return this;
    }

    @NotNull
    public final DTPacket addFlag(@NotNull PacketFlag flag) {
        Intrinsics.checkNotNullParameter(flag, "flag");
        this.flags.add(flag);
        return this;
    }

    @NotNull
    /* renamed from: setAuth  reason: collision with other method in class */
    public final DTPacket m3setAuth(@NotNull Auth auth) {
        Intrinsics.checkNotNullParameter(auth, "auth");
        this.auth = auth;
        return this;
    }

    @NotNull
    public final byte[] serialize() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ProtocolOutputStream $this$serialize_u24lambda_u2d1 = new ProtocolOutputStream(bytes);
        $this$serialize_u24lambda_u2d1.beginMessage(getId());
        if (!this.flags.isEmpty()) {
            $this$serialize_u24lambda_u2d1.writeByte(this.flags.size());
            $this$serialize_u24lambda_u2d1.beginFlags();
            Iterable $this$forEach$iv = this.flags;
            for (Object element$iv : $this$forEach$iv) {
                PacketFlag flag = (PacketFlag) element$iv;
                $this$serialize_u24lambda_u2d1.writeFlag(flag);
            }
            $this$serialize_u24lambda_u2d1.endFlags();
        }
        if (this.auth != null) {
            $this$serialize_u24lambda_u2d1.beginAuth();
            Auth auth = this.auth;
            Intrinsics.checkNotNull(auth);
            $this$serialize_u24lambda_u2d1.writeAuth(auth);
        }
        if (!this.data.isEmpty()) {
            $this$serialize_u24lambda_u2d1.writeDataObject(this.data);
        }
        $this$serialize_u24lambda_u2d1.endMessage();
        byte[] byteArray = bytes.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "bytes.toByteArray()");
        return byteArray;
    }

    @NotNull
    public String toString() {
        StringBuilder builder = new StringBuilder(Reflection.getOrCreateKotlinClass(getClass()).getSimpleName()).append("(");
        Iterable $this$forEach$iv = this.data;
        for (Object element$iv : $this$forEach$iv) {
            Intrinsics.checkNotNullExpressionValue(builder, "builder");
            if (builder.charAt(StringsKt.getLastIndex(builder)) != '(') {
                builder.append(", ");
            }
            builder.append(element$iv);
        }
        String sb = builder.append(")").toString();
        Intrinsics.checkNotNullExpressionValue(sb, "builder.append(\")\").toString()");
        return sb;
    }

    /* compiled from: DTPacket.kt */
    @Metadata(mv = {MagicNumber.FIELD_NULL, 6, MagicNumber.MESSAGE_ENDING}, k = MagicNumber.FIELD_NULL, xi = MagicNumber.FIELD_OBJECT, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J<\u0010\f\u001a\u0004\u0018\u00010\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lprotocol/DTPacket$Companion;", "", "()V", "createConstructorArgumentList", "", "constructor", "Lkotlin/reflect/KFunction;", "list", "deserialize", "Lprotocol/DTPacket;", "packet", "", "instancePacket", "clazz", "Lkotlin/reflect/KClass;", "flags", "Lprotocol/DTPacket$PacketFlag;", "timestamp", "", "data", "packetFromByteArray", "readData", "stream", "Ljava/io/DataInputStream;", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/DTPacket$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        private final List<Object> readData(DataInputStream stream) {
            Object readData;
            int readUnsignedShort = stream.readUnsignedShort();
            Object[] values = new Object[readUnsignedShort];
            for (int i = 0; i < readUnsignedShort; i++) {
                values[i] = null;
            }
            stream.skip(1L);
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                Object[] objArr = values;
                int i3 = i2;
                switch (stream.readUnsignedByte()) {
                    case MagicNumber.FIELD_NULL /* 1 */:
                        readData = null;
                        break;
                    case MagicNumber.FIELD_BOOLEAN /* 16 */:
                        readData = Boolean.valueOf(stream.readBoolean());
                        break;
                    case MagicNumber.FIELD_BYTE /* 17 */:
                        readData = Integer.valueOf(stream.readUnsignedByte());
                        break;
                    case MagicNumber.FIELD_SHORT /* 18 */:
                        readData = Integer.valueOf(stream.readUnsignedShort());
                        break;
                    case MagicNumber.FIELD_INT /* 19 */:
                        readData = Integer.valueOf(stream.readInt());
                        break;
                    case MagicNumber.FIELD_LONG /* 20 */:
                        readData = Long.valueOf(stream.readLong());
                        break;
                    case MagicNumber.FIELD_FLOAT /* 21 */:
                        readData = Float.valueOf(stream.readFloat());
                        break;
                    case MagicNumber.FIELD_DOUBLE /* 22 */:
                        readData = Double.valueOf(stream.readDouble());
                        break;
                    case MagicNumber.FIELD_STRING /* 32 */:
                        readData = stream.readUTF();
                        break;
                    case MagicNumber.FIELD_BOOLEAN_ARRAY /* 33 */:
                        int readUnsignedShort2 = stream.readUnsignedShort();
                        boolean[] zArr = new boolean[readUnsignedShort2];
                        for (int i4 = 0; i4 < readUnsignedShort2; i4++) {
                            zArr[i4] = stream.readBoolean();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = zArr;
                        break;
                    case MagicNumber.FIELD_BYTE_ARRAY /* 34 */:
                        int readUnsignedShort3 = stream.readUnsignedShort();
                        byte[] bArr = new byte[readUnsignedShort3];
                        for (int i5 = 0; i5 < readUnsignedShort3; i5++) {
                            bArr[i5] = (byte) stream.readUnsignedByte();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = bArr;
                        break;
                    case MagicNumber.FIELD_SHORT_ARRAY /* 35 */:
                        int readUnsignedShort4 = stream.readUnsignedShort();
                        short[] sArr = new short[readUnsignedShort4];
                        for (int i6 = 0; i6 < readUnsignedShort4; i6++) {
                            sArr[i6] = (short) stream.readUnsignedShort();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = sArr;
                        break;
                    case MagicNumber.FIELD_INT_ARRAY /* 36 */:
                        int readUnsignedShort5 = stream.readUnsignedShort();
                        int[] iArr = new int[readUnsignedShort5];
                        for (int i7 = 0; i7 < readUnsignedShort5; i7++) {
                            iArr[i7] = stream.readInt();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = iArr;
                        break;
                    case MagicNumber.FIELD_LONG_ARRAY /* 37 */:
                        int readUnsignedShort6 = stream.readUnsignedShort();
                        long[] jArr = new long[readUnsignedShort6];
                        for (int i8 = 0; i8 < readUnsignedShort6; i8++) {
                            jArr[i8] = stream.readLong();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = jArr;
                        break;
                    case MagicNumber.FIELD_FLOAT_ARRAY /* 38 */:
                        int readUnsignedShort7 = stream.readUnsignedShort();
                        float[] fArr = new float[readUnsignedShort7];
                        for (int i9 = 0; i9 < readUnsignedShort7; i9++) {
                            fArr[i9] = stream.readFloat();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = fArr;
                        break;
                    case MagicNumber.FIELD_DOUBLE_ARRAY /* 39 */:
                        int readUnsignedShort8 = stream.readUnsignedShort();
                        double[] dArr = new double[readUnsignedShort8];
                        for (int i10 = 0; i10 < readUnsignedShort8; i10++) {
                            dArr[i10] = stream.readDouble();
                        }
                        objArr = objArr;
                        i3 = i3;
                        readData = dArr;
                        break;
                    case MagicNumber.FIELD_OBJECT /* 48 */:
                        readData = readData(stream);
                        break;
                    default:
                }
                objArr[i3] = readData;
            }
            stream.skip(1L);
            return ArraysKt.toList(values);
        }

        private final List<Object> createConstructorArgumentList(KFunction<?> kFunction, List<? extends Object> list) {
            Object obj;
            Object obj2;
            List constructorArguments = new ArrayList();
            for (Pair pair : CollectionsKt.zip(kFunction.getParameters(), list)) {
                if (pair.getSecond() == null) {
                    if (((KParameter) pair.getFirst()).getType().isMarkedNullable()) {
                        constructorArguments.add(null);
                    } else {
                        System.out.println((Object) ("[DTPacket] Can't create instance for packet, " + ((KParameter) pair.getFirst()).getName() + " is not nullable but value is null"));
                        return null;
                    }
                } else {
                    if ((pair.getSecond() instanceof ArrayList) && Intrinsics.areEqual(((KParameter) pair.getFirst()).getType().getClassifier(), Reflection.getOrCreateKotlinClass(List.class))) {
                        KType type = ((KTypeProjection) ((KParameter) pair.getFirst()).getType().getArguments().get(0)).getType();
                        KClassifier classifier = type != null ? type.getClassifier() : null;
                        if (classifier == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                        }
                        KAnnotatedElement $this$hasAnnotation$iv = (KClass) classifier;
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
                        if (((PacketData) obj) != null) {
                            Object second = pair.getSecond();
                            Intrinsics.checkNotNull(second);
                            Iterable $this$map$iv = (List) second;
                            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                            for (Object item$iv$iv : $this$map$iv) {
                                KType type2 = ((KTypeProjection) ((KParameter) pair.getFirst()).getType().getArguments().get(0)).getType();
                                KClassifier classifier2 = type2 != null ? type2.getClassifier() : null;
                                KClass kClass = classifier2 instanceof KClass ? (KClass) classifier2 : null;
                                if (kClass != null) {
                                    KFunction primaryConstructor = KClasses.getPrimaryConstructor(kClass);
                                    if (primaryConstructor != null) {
                                        if (item$iv$iv == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
                                        }
                                        Collection $this$toTypedArray$iv = (List) item$iv$iv;
                                        Object[] array = $this$toTypedArray$iv.toArray(new Object[0]);
                                        if (array == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                                        }
                                        obj2 = primaryConstructor.call(Arrays.copyOf(array, array.length));
                                        destination$iv$iv.add(obj2);
                                    }
                                }
                                obj2 = null;
                                destination$iv$iv.add(obj2);
                            }
                            constructorArguments.add((List) destination$iv$iv);
                        }
                    }
                    Object second2 = pair.getSecond();
                    Intrinsics.checkNotNull(second2);
                    if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(second2.getClass()), ((KParameter) pair.getFirst()).getType().getClassifier())) {
                        Object second3 = pair.getSecond();
                        Intrinsics.checkNotNull(second3);
                        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(second3.getClass());
                        KClass classifier3 = ((KParameter) pair.getFirst()).getType().getClassifier();
                        if (classifier3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                        }
                        if (!KClasses.isSubclassOf(orCreateKotlinClass, classifier3)) {
                            if (pair.getSecond() instanceof List) {
                                KClass classifier4 = ((KParameter) pair.getFirst()).getType().getClassifier();
                                if (classifier4 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                                }
                                KFunction dataConstructor = KClasses.getPrimaryConstructor(classifier4);
                                if (dataConstructor != null) {
                                    Companion companion = DTPacket.Companion;
                                    Object second4 = pair.getSecond();
                                    if (second4 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
                                    }
                                    Collection nestedDataArgList = companion.createConstructorArgumentList(dataConstructor, (List) second4);
                                    if (nestedDataArgList != null) {
                                        Collection $this$toTypedArray$iv2 = nestedDataArgList;
                                        Object[] array2 = $this$toTypedArray$iv2.toArray(new Object[0]);
                                        if (array2 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                                        }
                                        constructorArguments.add(dataConstructor.call(Arrays.copyOf(array2, array2.length)));
                                    } else {
                                        return null;
                                    }
                                } else {
                                    return null;
                                }
                            } else {
                                System.out.println((Object) ("[DTPacket] Can't instance packet because of " + ((KParameter) pair.getFirst()).getName() + " with type " + ((KParameter) pair.getFirst()).getType()));
                                return null;
                            }
                        }
                    }
                    constructorArguments.add(pair.getSecond());
                }
            }
            return constructorArguments;
        }

        private final DTPacket instancePacket(KClass<?> kClass, List<? extends PacketFlag> list, long timestamp, List<? extends Object> list2) {
            Object obj;
            KFunction<?> primaryConstructor = KClasses.getPrimaryConstructor(kClass);
            if (primaryConstructor == null) {
                return null;
            }
            Collection constructorArguments = createConstructorArgumentList(primaryConstructor, list2);
            KFunction primaryConstructor2 = KClasses.getPrimaryConstructor(kClass);
            if (primaryConstructor2 == null) {
                obj = null;
            } else if (constructorArguments == null) {
                return null;
            } else {
                Collection $this$toTypedArray$iv = constructorArguments;
                Object[] array = $this$toTypedArray$iv.toArray(new Object[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                if (array == null) {
                    return null;
                }
                obj = primaryConstructor2.call(Arrays.copyOf(array, array.length));
            }
            Object obj2 = obj;
            DTPacket dTPacket = obj2 instanceof DTPacket ? (DTPacket) obj2 : null;
            if (dTPacket != null) {
                DTPacket packet = dTPacket;
                return packet.m1setTimestamp(timestamp).m2setFlags(list);
            }
            return null;
        }

        private final DTPacket packetFromByteArray(byte[] packet) {
            PacketID packetID;
            KClass pClass;
            PacketFlag packetFlag;
            DataInputStream $this$packetFromByteArray_u24lambda_u2d5 = new DataInputStream(new ByteArrayInputStream(packet));
            int pId = $this$packetFromByteArray_u24lambda_u2d5.readUnsignedShort();
            PacketID[] values = PacketID.values();
            int i = 0;
            int length = values.length;
            while (true) {
                if (i >= length) {
                    packetID = null;
                    break;
                }
                PacketID it = values[i];
                if (it.getValue() == pId) {
                    packetID = it;
                    break;
                }
                i++;
            }
            if (packetID == null || (pClass = packetID.getClazz()) == null) {
                throw new UnknownPacketException();
            }
            List flags = new ArrayList();
            List data = new ArrayList();
            $this$packetFromByteArray_u24lambda_u2d5.mark(10);
            $this$packetFromByteArray_u24lambda_u2d5.skip(1L);
            if ($this$packetFromByteArray_u24lambda_u2d5.readUnsignedByte() == 10) {
                $this$packetFromByteArray_u24lambda_u2d5.reset();
                $this$packetFromByteArray_u24lambda_u2d5.skip(1L);
                while (true) {
                    int flagId = $this$packetFromByteArray_u24lambda_u2d5.readUnsignedByte();
                    if (flagId == 15) {
                        break;
                    }
                    PacketFlag[] values2 = PacketFlag.values();
                    int i2 = 0;
                    int length2 = values2.length;
                    while (true) {
                        if (i2 >= length2) {
                            packetFlag = null;
                            break;
                        }
                        PacketFlag it2 = values2[i2];
                        if (it2.getValue() == flagId) {
                            packetFlag = it2;
                            break;
                        }
                        i2++;
                    }
                    if (packetFlag != null) {
                        PacketFlag flag = packetFlag;
                        flags.add(flag);
                    }
                }
            } else {
                $this$packetFromByteArray_u24lambda_u2d5.reset();
            }
            long createdTimestamp = $this$packetFromByteArray_u24lambda_u2d5.readLong();
            $this$packetFromByteArray_u24lambda_u2d5.mark(10);
            $this$packetFromByteArray_u24lambda_u2d5.skip(2L);
            if ($this$packetFromByteArray_u24lambda_u2d5.readUnsignedByte() == 170) {
                $this$packetFromByteArray_u24lambda_u2d5.reset();
                data.addAll(DTPacket.Companion.readData($this$packetFromByteArray_u24lambda_u2d5));
            } else {
                $this$packetFromByteArray_u24lambda_u2d5.reset();
            }
            try {
                DTPacket instancePacket = DTPacket.Companion.instancePacket(pClass, flags, createdTimestamp, data);
                if (instancePacket == null) {
                    throw new PacketConvertingException();
                }
                return instancePacket;
            } catch (Exception e) {
                throw new PacketConvertingException();
            }
        }

        @JvmStatic
        @NotNull
        public final DTPacket deserialize(@NotNull byte[] packet) {
            Object obj;
            Intrinsics.checkNotNullParameter(packet, "packet");
            try {
                return packetFromByteArray(packet);
            } catch (Exception e) {
                KAnnotatedElement $this$hasAnnotation$iv = Reflection.getOrCreateKotlinClass(e.getClass());
                Iterable $this$firstOrNull$iv$iv$iv = $this$hasAnnotation$iv.getAnnotations();
                Iterator it = $this$firstOrNull$iv$iv$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv$iv$iv = it.next();
                        Annotation it$iv$iv = (Annotation) element$iv$iv$iv;
                        if (it$iv$iv instanceof ProtocolException) {
                            obj = element$iv$iv$iv;
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                if (((ProtocolException) obj) != null) {
                    throw e;
                }
                throw new MalformedPacketException();
            }
        }
    }
}