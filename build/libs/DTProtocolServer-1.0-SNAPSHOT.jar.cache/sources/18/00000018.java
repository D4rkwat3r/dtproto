package protocol.packet;

import annotation.Packet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import protocol.DTPacket;

/* compiled from: P0x3Login.kt */
@Packet
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018��2\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Lprotocol/packet/P0x3Login;", "Lprotocol/DTPacket;", "deepId", "", "password", "(Ljava/lang/String;Ljava/lang/String;)V", "getDeepId", "()Ljava/lang/String;", "id", "Lprotocol/DTPacket$PacketID;", "getId", "()Lprotocol/DTPacket$PacketID;", "getPassword", "DTProtocolServer"})
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/packet/P0x3Login.class */
public final class P0x3Login extends DTPacket {
    @NotNull
    private final String deepId;
    @NotNull
    private final String password;
    @NotNull

    /* renamed from: id */
    private final DTPacket.PacketID f2id;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P0x3Login(@NotNull String deepId, @NotNull String password) {
        super(deepId, password);
        Intrinsics.checkNotNullParameter(deepId, "deepId");
        Intrinsics.checkNotNullParameter(password, "password");
        this.deepId = deepId;
        this.password = password;
        this.f2id = DTPacket.PacketID.LOGIN;
    }

    @NotNull
    public final String getDeepId() {
        return this.deepId;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @Override // protocol.DTPacket
    @NotNull
    public DTPacket.PacketID getId() {
        return this.f2id;
    }
}