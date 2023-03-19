package protocol.model;

import annotation.PacketData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import protocol.DTPacket;

/* compiled from: UserProfile.kt */
@PacketData
@Metadata(mv = {DTPacket.MagicNumber.FIELD_NULL, 6, DTPacket.MagicNumber.MESSAGE_ENDING}, k = DTPacket.MagicNumber.FIELD_NULL, xi = DTPacket.MagicNumber.FIELD_OBJECT, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018��2\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0003J7\u0010\u0015\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lprotocol/model/UserProfile;", "", "nickname", "", "deepId", "userId", "", "likes", "", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V", "getDeepId", "()Ljava/lang/String;", "getLikes", "()Ljava/util/List;", "getNickname", "getUserId", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "DTProtocolServer"})
/* loaded from: DTProtocolServer-1.0-SNAPSHOT.jar:protocol/model/UserProfile.class */
public final class UserProfile {
    @NotNull
    private final String nickname;
    @NotNull
    private final String deepId;
    private final long userId;
    @NotNull
    private final List<Long> likes;

    @NotNull
    public final String component1() {
        return this.nickname;
    }

    @NotNull
    public final String component2() {
        return this.deepId;
    }

    public final long component3() {
        return this.userId;
    }

    @NotNull
    public final List<Long> component4() {
        return this.likes;
    }

    @NotNull
    public final UserProfile copy(@NotNull String nickname, @NotNull String deepId, long userId, @NotNull List<Long> list) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        Intrinsics.checkNotNullParameter(deepId, "deepId");
        Intrinsics.checkNotNullParameter(list, "likes");
        return new UserProfile(nickname, deepId, userId, list);
    }

    public static /* synthetic */ UserProfile copy$default(UserProfile userProfile, String str, String str2, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userProfile.nickname;
        }
        if ((i & 2) != 0) {
            str2 = userProfile.deepId;
        }
        if ((i & 4) != 0) {
            j = userProfile.userId;
        }
        if ((i & 8) != 0) {
            list = userProfile.likes;
        }
        return userProfile.copy(str, str2, j, list);
    }

    @NotNull
    public String toString() {
        return "UserProfile(nickname=" + this.nickname + ", deepId=" + this.deepId + ", userId=" + this.userId + ", likes=" + this.likes + ')';
    }

    public int hashCode() {
        int result = this.nickname.hashCode();
        return (((((result * 31) + this.deepId.hashCode()) * 31) + Long.hashCode(this.userId)) * 31) + this.likes.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) other;
            return Intrinsics.areEqual(this.nickname, userProfile.nickname) && Intrinsics.areEqual(this.deepId, userProfile.deepId) && this.userId == userProfile.userId && Intrinsics.areEqual(this.likes, userProfile.likes);
        }
        return false;
    }

    public UserProfile(@NotNull String nickname, @NotNull String deepId, long userId, @NotNull List<Long> list) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        Intrinsics.checkNotNullParameter(deepId, "deepId");
        Intrinsics.checkNotNullParameter(list, "likes");
        this.nickname = nickname;
        this.deepId = deepId;
        this.userId = userId;
        this.likes = list;
    }

    @NotNull
    public final String getNickname() {
        return this.nickname;
    }

    @NotNull
    public final String getDeepId() {
        return this.deepId;
    }

    public final long getUserId() {
        return this.userId;
    }

    @NotNull
    public final List<Long> getLikes() {
        return this.likes;
    }
}