
package net.openmob.mobileimsdk.server.event;

import net.openmob.mobileimsdk.server.protocal.Protocal;
import net.openmob.mobileimsdk.server.protocal.c.PLoginInfo;
import org.apache.mina.core.session.IoSession;

public interface ServerEventListener {
    int onVerifyUserCallBack(PLoginInfo loginInfo, IoSession session);

    void onUserLoginAction_CallBack(int paramInt, String paramString, IoSession paramIoSession);

    void onUserLogoutAction_CallBack(int paramInt, Object paramObject);

    boolean onTransBuffer_CallBack(int paramInt1, int paramInt2, String paramString1, String paramString2);

    void onTransBuffer_C2C_CallBack(int paramInt1, int paramInt2, String paramString);

    boolean onTransBuffer_C2C_RealTimeSendFaild_CallBack(Protocal pFromClient);
}