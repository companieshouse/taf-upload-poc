package uk.gov.companieshouse.taf.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import io.magentys.cinnamon.guice.CinnamonModule;
import uk.gov.ch.taf.common.guice.TafCommonModule;


public class GuiceInjectorSource implements InjectorSource {
    @Override
    public Injector getInjector() {
        return Guice.createInjector(
                CucumberModules.createScenarioModule(),
                new CinnamonModule(),
                new ProjectModule(),
                new TafCommonModule()
        );
    }
}