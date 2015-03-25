package com.sysdbg.fastrun;

import java.io.IOException;

import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.WinDef;

/**
 * Created by xinzhao on 3/24/2015.
 */
public class NewProcessExecutor implements Executor {
    private static final int SW_SHOWNORMAL = 1;
    private static final int SUCCESS_CODE_START = 32;
    private static final String OPEN = "open";

    @Override
    public void run(Item item) throws SecurityException, IOException, IllegalArgumentException {
        // check item is NewProcessItem
        if (!(item instanceof NewProcessItem)) {
            throw new IllegalArgumentException("item is not a NewProcessItem");
        }

        // check empty command
        NewProcessItem new_process_item = (NewProcessItem) item;
        if (new_process_item.getCommandLine().length == 0) {
            throw new IOException("empty command line");
        }

        // create process
        String file_name = new_process_item.getCommandLine()[0];    // first element is file neme
        String parameters = makeParameter(new_process_item.getCommandLine());   // remaining is parameters
        WinDef.INT_PTR result = Shell32.INSTANCE.ShellExecute(
                null, OPEN, file_name, parameters, null, SW_SHOWNORMAL);
        checkSuccess(result);
    }

    // check if ShellExecute return value is successful value, if not throw IOExcpetion
    private void checkSuccess(WinDef.INT_PTR result) throws IOException {
        if (result.intValue() > SUCCESS_CODE_START) {
            return;
        }

        throw new IOException(String.format("ShellExecute fail with status code %d", result.intValue()));
    }

    // "commandLine[1]" + " commandLine[2]" + ...
    private String makeParameter(String[] commandLine) {
        if (commandLine.length <= 1)
            return null;

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < commandLine.length; ++i) {
            sb.append("\"");
            sb.append(commandLine[i]);
            sb.append("\" ");
        }
        sb.deleteCharAt(sb.length() - 1);   // delete last space

        return sb.toString();
    }
}
