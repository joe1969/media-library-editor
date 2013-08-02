package de.ravenfly.mle.metadata;

import de.ravenfly.mle.modulebase.DataFeatures;

public class DataFeaturesImpl implements DataFeatures {

	@Override
	public boolean canLoad() {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}
}
