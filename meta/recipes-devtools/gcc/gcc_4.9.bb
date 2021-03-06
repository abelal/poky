require recipes-devtools/gcc/gcc-${PV}.inc
require gcc-target.inc

# Building with thumb enabled on armv4t fails with
# | gcc-4.8.1-r0/gcc-4.8.1/gcc/cp/decl.c:7438:(.text.unlikely+0x2fa): relocation truncated to fit: R_ARM_THM_CALL against symbol `fancy_abort(char const*, int, char const*)' defined in .glue_7 section in linker stubs
# | gcc-4.8.1-r0/gcc-4.8.1/gcc/cp/decl.c:7442:(.text.unlikely+0x318): additional relocation overflows omitted from the output
ARM_INSTRUCTION_SET_armv4 = "arm"

BBCLASSEXTEND = "nativesdk"

#SYSTEMHEADERS_class-nativesdk = "${@'${target_includedir}'.replace(d.getVar('SDKPATH', True),'%r')}"
#SYSTEMLIBS_class-nativesdk = "${@'${target_base_libdir}'.replace(d.getVar('SDKPATH', True),'%r')}/"
#SYSTEMLIBS1_class-nativesdk = "${@'${target_libdir}'.replace(d.getVar('SDKPATH', True),'%r')}/"

