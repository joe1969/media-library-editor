package de.ravenfly.mle.metadata;

import de.ravenfly.mle.modulebase.DataFeatures;
import de.ravenfly.mle.episode.filemodel.Episode;

public class DataFeaturesImpl implements DataFeatures {

	@Override
	public boolean canLoad() {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String getModelClassName() {
		return Episode.class.getCanonicalName();
	}

	@Override
	public Class<?> getModelClass() {
		return Episode.class;
	}
}
