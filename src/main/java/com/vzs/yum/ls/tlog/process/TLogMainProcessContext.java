package com.vzs.yum.ls.tlog.process;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/7/16.
 */
@Builder
@Getter
public class TLogMainProcessContext {
    private String tLogFolderPath;
    private List<Date> selectionDate;
    @Setter
    private boolean isDebug;
}
