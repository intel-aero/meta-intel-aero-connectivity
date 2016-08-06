HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README;md5=7f393579f8b109fe91f3b9765d26c7d3"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"


SRC_URI = " \
           http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
           file://hostapd.conf \
           file://defconfig \
           file://udhcpd.conf \
"

S = "${WORKDIR}/hostapd-${PV}"


CONFFILES_${PN} += "${sysconfdir}/hostapd.conf \
                    ${sysconfdir}/udhcpd.conf \
"

FILES_${PN} = "/usr/sbin \
               /usr/local/bin/hostapd \
               /usr/local/bin/hostapd_cli \
               ${sysconfdir}/hostapd.conf \
               ${sysconfdir}/udhcpd.conf \
"

FILES_${PN}-dbg = "/usr/local/bin/.debug/hostapd \
                   /usr/local/bin/.debug/hostapd_cli \
                   /usr/src/debug/hostapd/"


do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/hostapd/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -Wextra -Wno-unused-parameter -g -I${STAGING_INCDIR}/libnl3"
    cd "${S}/hostapd/"
    make -j4
}

do_install() {
    cd "${S}/hostapd/"
    make install DESTDIR=${D}
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}
}

SRC_URI[md5sum] = "69f9cec3f76d74f402864a43e4f8624f"
SRC_URI[sha256sum] = "8e272d954dc0d7026c264b79b15389ec2b2c555b32970de39f506b9f463ec74a"

