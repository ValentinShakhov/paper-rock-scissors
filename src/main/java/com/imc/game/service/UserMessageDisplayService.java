package com.imc.game.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(UserMessageDisplayConsoleService.class)
interface UserMessageDisplayService {

    String ANY_MESSAGE_PREFIX = "###";

    void display(String message);
}
