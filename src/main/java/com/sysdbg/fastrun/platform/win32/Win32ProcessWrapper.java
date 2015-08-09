package com.sysdbg.fastrun.platform.win32;

import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.WinDef;
import com.sysdbg.fastrun.platform.ProcessWrapper;
import org.springframework.stereotype.Component;

/**
 * Created by crady on 8/5/2015.
 */
@Component
public class Win32ProcessWrapper implements ProcessWrapper {
    private static final String OPEN = "open";

    @Override
    public int createProcess(String imagePath, String arguments) {
        WinDef.INT_PTR ptr = Shell32.INSTANCE.ShellExecute(null, "open", imagePath, arguments, null, 1);
        return ptr.intValue();
    }
}
