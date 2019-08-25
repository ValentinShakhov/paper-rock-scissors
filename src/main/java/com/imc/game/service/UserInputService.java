package com.imc.game.service;

import com.google.inject.ImplementedBy;
import com.imc.game.entity.Gesture;

@ImplementedBy(UserInputConsoleService.class)
public
interface UserInputService {

    long getNumberOfRounds();

    Gesture getUserGesture();
}
