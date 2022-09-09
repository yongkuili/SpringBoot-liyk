package com.liyk.backup.service.impl;

import com.liyk.backup.service.MysqlBackupService;
import com.liyk.backup.util.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @author liyongkui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/24 09:11
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
                          String database) throws Exception {
        return MySqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
            throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
    }
}
