package com.imc.game.configuration;

import com.google.inject.ImplementedBy;
import com.imc.game.entity.Gesture;

import java.util.List;

@ImplementedBy(GesturesMapBasedConfiguration.class)
public interface GesturesConfiguration {

    List<Gesture> getWeakerGesturesThan(final Gesture gesture);
}

