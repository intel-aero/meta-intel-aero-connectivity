IMAGE_INSTALL += "backport-iwlwifi"
IMAGE_INSTALL += "linux-firmware-iwlwifi-8000c"
IMAGE_INSTALL += "hostapd"
IMAGE_INSTALL += "autostart-hostapd"
IMAGE_INSTALL += "autostart-supplicant"

# LTE MODEM
IMAGE_INSTALL += "glibc-gconvs glibc-utils glibc-gconv-iso8859-1 modemmanager \
	opkg opkg-utils rpm icon-naming-utils libtool libndp libnl libinput \
	libxdmcp networkmanager autostart-modem modem-enable \
"
