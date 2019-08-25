package com.imc.game.service;

class UserMessageDisplayConsoleService implements UserMessageDisplayService {

    @Override
    public void display(final String message) {
        System.out.println(String.format("%s %s", ANY_MESSAGE_PREFIX, message));
    }
}
