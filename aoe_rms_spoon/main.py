import argparse
import sys

from aoe_rms_spoon.versions.aoe_2_de.AoEDEIIVersion import AoEDEIIVersion


def options(args):
    parser = argparse.ArgumentParser(prog="age-rms-spoon",description="""
        This programs allows you to use python to write a modelled (albeit simple!) version of the RMS file.
        Then you can use such a model to create the RMS file. This is use because you can use python to perform 
        any mathematical operation or automatically generate RMS code.
        
        We also provide some semantics checks, albeit limited.
        
    """)
    parser.add_argument("file", nargs=1, type=str, help="""
    A python file which contains an implementation of the class AbstractRMSScriptGenerator. The class is the only thing
    you need to in order to generate the file. At the end of the class, you should put the string:
    
    SCRIPT = yourImplementationOfAbstractRMSScriptGenerator()

    """)
    parser.add_argument("--aoe-version", type=str, help="""
    Specifies the version of the Aoge of empires. Different versions behave differently.
    At the moment only "AoEDEIIVersion" is allowed
    """)

    return parser.parse_args(args)


def load_module(module: str):
    # https://stackoverflow.com/a/47559089/1887602

    # module_path = "mypackage.%s" % module
    module_path = module

    if module_path in sys.modules:
        return sys.modules[module_path]

    return __import__(module_path, fromlist=[module])


def get_aoe_version(name: str):
    if name == "AoEDEIIVersion":
        return AoEDEIIVersion()
    else:
        raise ValueError(f"Invalid aoe version '{name}'!")


def main():
    o = options(sys.argv[1:])

    version = get_aoe_version(o.aoe_version)
    # load file



if __name__ == "__main__":
    main()