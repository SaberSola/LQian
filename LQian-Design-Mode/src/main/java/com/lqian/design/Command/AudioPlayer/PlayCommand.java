package com.lqian.design.Command.AudioPlayer;

public class PlayCommand implements Command {

    private AudioPlayer myAudio;

    public PlayCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }


    @Override
    public void execute() {
        myAudio.play();
    }
}
