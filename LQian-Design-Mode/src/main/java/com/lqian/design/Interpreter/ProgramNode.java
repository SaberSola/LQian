package com.lqian.design.Interpreter;

public class ProgramNode extends Node  {

    private Node commandListNode;

    @Override
    public void parse(Context context) throws ParseException {

        context.skipToken("program");
        commandListNode = new CommandListNode();
    }

    public String toString() {
        return "[program " + commandListNode + "]";
    }
}
