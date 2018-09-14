package com.lqian.design.Command;

import java.util.Iterator;
import java.util.Stack;

public class MacroCommand implements Command {

    private Stack commands = new Stack();

    @Override
    public void execute() {
        Iterator it = commands.iterator();
        while (it.hasNext()){
            ((Command)it.next()).execute();
        }
    }

    /**
     * 添加命令
     */
    public void append(Command cmd){
        if (cmd != this){
            commands.push(cmd);
        }
    }

    /**
     * 删除最后一条命令
     */
    public void undo(){
        if (!commands.empty()){
            commands.pop();
        }
    }
    /**
     * 清空命令
     */
    public void clear(){
        commands.clear();
    }
}
