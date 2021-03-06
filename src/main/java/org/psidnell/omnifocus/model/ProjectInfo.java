/*
 * Copyright 2015 Paul Sidnell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.psidnell.omnifocus.model;

import org.psidnell.omnifocus.sqlite.SQLiteProperty;

/**
 * @author psidnell
 *
 *         Represents data loaded from the ProjectInfo table.
 *
 *         Projects are constructed from this and their root task.
 */
public class ProjectInfo {

    private String task;
    private String folderId;
    private String status;
    private boolean singleActionList = false;

    @SQLiteProperty(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SQLiteProperty(name = "folder")
    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    @SQLiteProperty(name = "task")
    public String getRootTaskId() {
        return task;
    }

    public void setRootTaskId(String task) {
        this.task = task;
    }

    @SQLiteProperty(name = "containsSingletonActions")
    public boolean isSingleActionList() {
        return singleActionList;
    }

    public void setSingleActionList(boolean singleActionList) {
        this.singleActionList = singleActionList;
    }
}
