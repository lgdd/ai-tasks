/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import fi.soveltia.liferay.aitasks.model.AITaskTable;
import fi.soveltia.liferay.aitasks.model.impl.AITaskImpl;
import fi.soveltia.liferay.aitasks.model.impl.AITaskModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from AITask.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=fi.soveltia.liferay.aitasks.model.impl.AITaskImpl",
		"table.name=AITask"
	},
	service = ArgumentsResolver.class
)
public class AITaskModelArgumentsResolver implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		AITaskModelImpl aiTaskModelImpl = (AITaskModelImpl)baseModel;

		long columnBitmask = aiTaskModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(aiTaskModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |= aiTaskModelImpl.getColumnBitmask(
					columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(aiTaskModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return AITaskImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return AITaskTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		AITaskModelImpl aiTaskModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = aiTaskModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = aiTaskModelImpl.getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}