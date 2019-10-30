package uk.gov.companieshouse.taf.domain;

import javax.inject.Inject;

import uk.gov.companieshouse.taf.pages.BaseObject;

public class GenericDomain extends BaseDomain {

    @Inject
    private BaseObject baseObject;

    public void clickBrowserBack() {
        baseObject.clickBrowserBack();
    }
}
