package tools;


import com.rong360.im.rest.MessageSenderResource;
import org.restlet.Component;
import org.restlet.data.Protocol;
import tools.server.TextFileServer;
import tools.utils.CheckArgs;
import tools.utils.TypeUtils;

/**
 * Created by chao.cheng on 2016/4/21.
 */
public final class Main {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        if (!CheckArgs.isEmpty(args)) {
            for (int i = 0; i < args.length; i++) {
                if ("-p".equals(args[i]) && i < args.length - 1) {
                    port = TypeUtils.toInt(args[i + 1], port);
                }
            }
        }

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, port);
        component.getDefaultHost().attach("/test", TextFileServer.class);
        component.getDefaultHost().attach("/", TextFileServer.class);
        component.getDefaultHost().attach("/sender", MessageSenderResource.class);
        component.start();
    }

}
