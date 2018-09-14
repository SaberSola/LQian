package com.lqian.design.Command.AudioPlayer;

public class RewindCommand implements Command {


    private AudioPlayer myAudio;

    public RewindCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }

    @Override
    public void execute() {
        myAudio.rewind();
    }
}
