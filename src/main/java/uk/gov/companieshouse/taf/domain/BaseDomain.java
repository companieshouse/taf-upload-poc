package uk.gov.companieshouse.taf.domain;

import io.magentys.commons.typemap.TypedKey;

import javax.inject.Inject;

import uk.gov.ch.taf.common.memory.Memory;
import uk.gov.companieshouse.taf.pages.BaseObject;

public class BaseDomain implements Memory {

    private Memory memory;

    @Inject
    private BaseObject baseObject;

    @Inject
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public <T> Memory commit(TypedKey<T> typedKey, T type) {
        return memory.commit(typedKey, type);
    }

    @Override
    public <T> T recall(TypedKey<T> typedKey) {
        return memory.recall(typedKey);
    }

    @Override
    public <T> T recall(TypedKey<T> typedKey, T type) {
        return memory.recall(typedKey, type);
    }

    public void clickContinue() {
        baseObject.clickContinue();
    }
}
