package protocol;

import annotation.PacketData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import protocol.DTPacket;

/* compiled from: Auth.kt */
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lprotocol/Auth;", "", "signature", "", "data", "Lprotocol/Auth$PacketAuth;", "([BLprotocol/Auth$PacketAuth;)V", "getData", "()Lprotocol/Auth$PacketAuth;", "getSignature", "()[B", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PacketAuth", "DTProtocolServer"})
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/Auth.class */
public final class Auth {
    @NotNull
    private final byte[] signature;
    @NotNull
    private final PacketAuth data;

    @NotNull
    public final byte[] component1() {
        return this.signature;
    }

    @NotNull
    public final PacketAuth component2() {
        return this.data;
    }

    @NotNull
    public final Auth copy(@NotNull byte[] signature, @NotNull PacketAuth data) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(data, "data");
        return new Auth(signature, data);
    }

    public static /* synthetic */ Auth copy$default(Auth auth, byte[] bArr, PacketAuth packetAuth, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = auth.signature;
        }
        if ((i & 2) != 0) {
            packetAuth = auth.data;
        }
        return auth.copy(bArr, packetAuth);
    }

    @NotNull
    public String toString() {
        return "Auth(signature=" + Arrays.toString(this.signature) + ", data=" + this.data + ')';
    }

    public Auth(@NotNull byte[] signature, @NotNull PacketAuth data) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(data, "data");
        this.signature = signature;
        this.data = data;
    }

    @NotNull
    public final byte[] getSignature() {
        return this.signature;
    }

    @NotNull
    public final PacketAuth getData() {
        return this.data;
    }

    /* compiled from: Auth.kt */
    @PacketData
    @Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0087\b\u0018��2\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\b\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\bHÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001a"}, d2 = {"Lprotocol/Auth$PacketAuth;", "", "userId", "", "role", "Lprotocol/DTPacket$Role;", "authTime", "(JLprotocol/DTPacket$Role;J)V", "", "(JBJ)V", "getAuthTime", "()J", "getRole", "()B", "getUserId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DTProtocolServer"})
    /* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/Auth$PacketAuth.class */
    public static final class PacketAuth {
        private final long userId;
        private final byte role;
        private final long authTime;

        public final long component1() {
            return this.userId;
        }

        public final byte component2() {
            return this.role;
        }

        public final long component3() {
            return this.authTime;
        }

        @NotNull
        public final PacketAuth copy(long userId, byte role, long authTime) {
            return new PacketAuth(userId, role, authTime);
        }

        public static /* synthetic */ PacketAuth copy$default(PacketAuth packetAuth, long j, byte b, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = packetAuth.userId;
            }
            if ((i & 2) != 0) {
                b = packetAuth.role;
            }
            if ((i & 4) != 0) {
                j2 = packetAuth.authTime;
            }
            return packetAuth.copy(j, b, j2);
        }

        @NotNull
        public String toString() {
            return "PacketAuth(userId=" + this.userId + ", role=" + ((int) this.role) + ", authTime=" + this.authTime + ')';
        }

        public int hashCode() {
            int result = Long.hashCode(this.userId);
            return (((result * 31) + Byte.hashCode(this.role)) * 31) + Long.hashCode(this.authTime);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof PacketAuth) {
                PacketAuth packetAuth = (PacketAuth) other;
                return this.userId == packetAuth.userId && this.role == packetAuth.role && this.authTime == packetAuth.authTime;
            }
            return false;
        }

        public PacketAuth(long userId, byte role, long authTime) {
            this.userId = userId;
            this.role = role;
            this.authTime = authTime;
        }

        public final long getUserId() {
            return this.userId;
        }

        public final byte getRole() {
            return this.role;
        }

        public final long getAuthTime() {
            return this.authTime;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public PacketAuth(long userId, @NotNull DTPacket.Role role, long authTime) {
            this(userId, role.getValue(), authTime);
            Intrinsics.checkNotNullParameter(role, "role");
        }
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            if (other == null) {
                throw new NullPointerException("null cannot be cast to non-null type protocol.Auth");
            }
            Auth auth = (Auth) other;
            return Arrays.equals(this.signature, ((Auth) other).signature) && Intrinsics.areEqual(this.data, ((Auth) other).data);
        }
        return false;
    }

    public int hashCode() {
        int result = Arrays.hashCode(this.signature);
        return (31 * result) + this.data.hashCode();
    }
}