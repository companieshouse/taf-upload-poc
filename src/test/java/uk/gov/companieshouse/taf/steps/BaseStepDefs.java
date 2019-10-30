package uk.gov.companieshouse.taf.steps;

import io.magentys.commons.typemap.TypedKey;

import javax.inject.Inject;

import uk.gov.ch.taf.common.memory.Memory;

import uk.gov.companieshouse.taf.domain.FileUploadDomain;

public class BaseStepDefs implements Memory {

    @Inject
    private FileUploadDomain fileUploadDomain;

    @Inject
    public Memory memory;

    /**
     * Open given url.
     */
    public void goToSignInScreen() {
        fileUploadDomain.navigateToWebPage();
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
}
