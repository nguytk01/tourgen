package tourgen.controller;

import java.security.Permission;

public class SecurityManagerMock extends SecurityManager {

	private SecurityManager baseSecurityManager;

	public SecurityManagerMock(SecurityManager baseSecurityManager) {
		this.baseSecurityManager = baseSecurityManager;
	}

	@Override
	public void checkPermission(Permission permission) {
		if (permission.getName().startsWith("exitVM")) {
				throw new SecurityException("This error is normal. It is there to prevent System.exit() and keep unit tests running.");
		}
		if (baseSecurityManager != null) {
			baseSecurityManager.checkPermission(permission);
		} else {
			return;
		}
	}

}
