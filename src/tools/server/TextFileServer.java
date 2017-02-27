package tools.server;


import com.rong360.im.common.CheckArgs;
import org.restlet.data.Form;
import org.restlet.representation.InputRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import tools.file.TextFileReader;
import tools.utils.TypeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by chao.cheng on 2016/4/23.
 */
public class TextFileServer extends ServerResource {

    @Get
    @Post
    public Object action() throws FileNotFoundException {
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String action = form.getFirstValue("action");
        String filePath = form.getFirstValue("path");
        if (CheckArgs.Strings.isEmpty(filePath)) {
            return "filePath is null";
        }
        if ("download".equals(action)) {
            return new InputRepresentation(new FileInputStream(new File(filePath)));
        } else if ("search".equals(action)) {
            return readFile(form, filePath);
        }
        return ("Not Support Action:" + action);
    }


    private String readFile(Form form, String filePath) {
        String keyword = form.getFirstValue("keyword");
        String before = form.getFirstValue("up");
        String after = form.getFirstValue("down");
        int up = TypeUtils.toInt(before, 0);
        int down = TypeUtils.toInt(after, 0);
        TextFileReader fileReader = new TextFileReader(filePath);
        return fileReader.findLine(keyword, up, down);
    }
}
