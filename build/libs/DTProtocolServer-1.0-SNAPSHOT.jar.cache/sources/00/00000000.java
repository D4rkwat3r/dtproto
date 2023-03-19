package p000;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import protocol.Auth;
import protocol.DTPacket;
import protocol.model.UserProfile;
import protocol.p001io.ProtocolInputStream;
import protocol.packet.P0x5AckAuth;

/* compiled from: main.kt */
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = 2, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010��\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V", "DTProtocolServer"})
/* renamed from: MainKt  reason: from Kotlin metadata */
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:MainKt.class */
public final class main {
    public static final void main(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        byte[] signature = new byte[32];
        Random.Default.nextBytes(signature);
        byte[] packet = new P0x5AckAuth(CollectionsKt.listOf(new UserProfile[]{new UserProfile("Darkwater", "D4rkwat3r", 12345L, CollectionsKt.listOf(new Long[]{1L, 5L, 6L, 4L, Long.valueOf(System.currentTimeMillis())})), new UserProfile("Gestalt", "GSCC", 123456L, CollectionsKt.listOf(new Long[]{1L, 5L, 6L, 7L, 3L}))})).setAuth(new Auth(signature, new Auth.PacketAuth(1L, DTPacket.Role.ADMIN, System.currentTimeMillis()))).serialize();
        File $this$main_u24lambda_u2d0 = new File("packet.bin");
        $this$main_u24lambda_u2d0.createNewFile();
        FilesKt.writeBytes($this$main_u24lambda_u2d0, packet);
        ProtocolInputStream $this$main_u24lambda_u2d1 = new ProtocolInputStream(new ByteArrayInputStream(packet));
        System.out.println((Object) ("Packet ID: " + $this$main_u24lambda_u2d1.beginMessage()));
        if (($this$main_u24lambda_u2d1.m8uByteAfterWa3L5BU(1) & 255) == 10) {
            int flagCount = $this$main_u24lambda_u2d1.nextByte();
            $this$main_u24lambda_u2d1.beginFlags();
            StringBuilder append = new StringBuilder().append("Flag count: ").append(flagCount).append(", flags: ");
            DTPacket.PacketFlag[] packetFlagArr = new DTPacket.PacketFlag[flagCount];
            for (int i = 0; i < flagCount; i++) {
                packetFlagArr[i] = $this$main_u24lambda_u2d1.readFlag();
            }
            System.out.println((Object) append.append(ArraysKt.toList(packetFlagArr)).toString());
            $this$main_u24lambda_u2d1.endFlags();
        } else {
            System.out.println((Object) "This packet have no any flags");
        }
        if (($this$main_u24lambda_u2d1.m8uByteAfterWa3L5BU(0) & 255) == 26) {
            System.out.println((Object) ("Packet have auth header. UserID: " + $this$main_u24lambda_u2d1.readAuth().getData().getUserId()));
        } else {
            System.out.println((Object) "Packet have no auth header");
        }
        if (($this$main_u24lambda_u2d1.m8uByteAfterWa3L5BU(1) & 255) == 170) {
            List data = $this$main_u24lambda_u2d1.nextDataObject();
            System.out.println((Object) ("Packet have DATA-object. Root object fields count: " + data.size() + "\nRoot object fields: " + CollectionsKt.toList(data)));
            return;
        }
        System.out.println((Object) "Packet have no DATA-object");
    }
}