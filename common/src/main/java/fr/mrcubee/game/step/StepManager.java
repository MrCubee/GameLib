package fr.mrcubee.game.step;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.event.EventUtils;
import fr.mrcubee.game.event.GameStepCompleteEvent;
import fr.mrcubee.game.event.GameStepDeleteEvent;
import fr.mrcubee.game.event.GameStepStartEvent;

import java.util.ArrayList;
import java.util.List;

public class StepManager<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> {

    private final G game;
    private final List<Step<P, S, G>> steps;
    private int currentStep;

    public StepManager(final G game) {
        this.game = game;
        this.steps = new ArrayList<Step<P, S, G>>();
        this.currentStep = -1;
    }

    public boolean registerStep(final Step<P, S, G> step) {
        if (step == null || this.steps.contains(step))
            return false;
        this.steps.add(step);
        return true;
    }

    public boolean unRegisterStep(final Step<P, S, G> step) {
        if (step == null)
            return false;
        return this.steps.remove(step);
    }

    public Step<P, S, G> getStep(final int index) {
        if (index < 0 || index >= this.steps.size())
            return null;
        return this.steps.get(index);
    }

    public Step<P, S, G> getStep(final String name) {
        final int hashCode;

        if (name == null)
            return null;
        hashCode = name.hashCode();
        for (Step<P, S, G> step : this.steps) {
            if (step.hashCode() == hashCode)
                return step;
        }
        return null;
    }

    public int getStepIndex(final Step<P, S, G> step) {
        if (step == null)
            return -1;
        return this.steps.indexOf(step);
    }

    public Step<P, S, G> getCurrentStep() {
        return getStep(this.currentStep);
    }

    public Step<P, S, G> getLastStep() {
        return getStep(this.currentStep - 1);
    }

    public Step<P, S, G> getNextStep() {
        return getStep(this.currentStep + 1);
    }

    public void nextStep() {
        final Step<P, S, G> nextStep;
        Step<P, S, G> step;

        if (this.currentStep >= this.steps.size() || this.game.getGameStats().ordinal() <= 2)
            return;
        nextStep = getNextStep();
        step = getLastStep();
        if (step != null) {
            EventUtils.callEvent(new GameStepDeleteEvent(this.game, step));
            step.remove();
        }
        step = getCurrentStep();
        if (step != null) {
            EventUtils.callEvent(new GameStepCompleteEvent(this.game, step));
            step.complete();
        }
        if (nextStep != null) {
            EventUtils.callEvent(new GameStepStartEvent(this.game, nextStep));
            nextStep.start();
        }
        this.currentStep++;
    }

    public void prevStep() {
        final Step<P, S, G> prevStep;
        Step<P, S, G> step;

        if (this.currentStep < 0 || this.game.getGameStats().ordinal() <= 2)
            return;
        prevStep = getLastStep();
        step = getNextStep();
        if (step != null) {
            EventUtils.callEvent(new GameStepDeleteEvent(this.game, step));
            step.remove();
        }
        step = getCurrentStep();
        if (step != null) {
            EventUtils.callEvent(new GameStepCompleteEvent(this.game, step));
            step.complete();
        }
        if (prevStep != null) {
            EventUtils.callEvent(new GameStepStartEvent(this.game, prevStep));
            prevStep.start();
        }
        this.currentStep--;
    }

}
