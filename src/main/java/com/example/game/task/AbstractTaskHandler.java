package com.example.game.task;

public abstract class AbstractTaskHandler {

    protected void before(AbstractGameTask messageTask) {
    }

    protected void after(AbstractGameTask messageTask) {
    }

    public void execute(AbstractGameTask messageTask) {
        this.before(messageTask);
        this.handle(messageTask);
        this.after(messageTask);
    }

    protected abstract void handle(AbstractGameTask messageTask);
}
