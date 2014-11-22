/*
Copyright 2014 Paul Sidnell

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.psidnell.omnifocus.model;

import java.util.LinkedList;
import java.util.List;

import org.psidnell.omnifocus.sqlite.SQLiteProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Task extends CommonProjectTask {

    public static final String TYPE = "Task";

    private String containingProject;
    private boolean next;
    private String parentTaskId;
    private String contextId;
    private Task parent;
    private Project project;
    private boolean blocked = false;
    
    @SQLiteProperty
    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @SQLiteProperty(name = "context")
    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    @SQLiteProperty(name = "parent")
    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentFolderId) {
        this.parentTaskId = parentFolderId;
    }

    public String getContainingProject() {
        return containingProject;
    }

    public void setContainingProject(String containingProject) {
        this.containingProject = containingProject;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return TYPE;
    }

    public boolean getNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    @JsonIgnore
    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    @Override
    public List<Node> getProjectPath() {
        if (parent != null) {
            return getProjectPath(parent);
        } else if (project != null) {
            return getProjectPath(project);
        } else {
            // Inbox task? TODO
            LinkedList<Node> result = new LinkedList<>();
            result.add(this);
            return result;
        }
    }

    @JsonIgnore
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public void add(Task child) {
        tasks.add(child);
        Task oldParent = child.getParent();
        child.setParent(this);
        if (oldParent != null) {
            oldParent.getTasks().remove(child);
        }
    }

    @Override
    public boolean isAvailable() {
        return !isCompleted() && !getBlocked();
    }

    @Override
    public boolean isRemaining() {
        return !isCompleted();
    }
}
