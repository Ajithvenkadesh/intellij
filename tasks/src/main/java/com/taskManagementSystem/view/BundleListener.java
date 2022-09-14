package bundle;

import org.osgi.framework.BundleEvent;
import java.util.EventListener;

public interface BundleListener extends EventListener {

    public void bundleChanged(BundleEvent event);
}