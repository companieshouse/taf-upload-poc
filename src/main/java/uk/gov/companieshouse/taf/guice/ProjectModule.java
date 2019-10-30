package uk.gov.companieshouse.taf.guice;

import com.google.inject.AbstractModule;
import cucumber.runtime.java.guice.ScenarioScoped;
import uk.gov.ch.taf.common.memory.Memory;
import uk.gov.ch.taf.common.memory.MemoryImpl;

public final class ProjectModule extends AbstractModule {
    @Override
    public void configure() {
        try {
            // Bindings for classes that are shared for the lifetime of the
            // scenario.
            bind(Memory.class).to(MemoryImpl.class).in(ScenarioScoped.class);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
}
