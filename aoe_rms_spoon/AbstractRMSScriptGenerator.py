import abc
from typing import Tuple

from aoe_rms_spoon.AbstractAoeVersion import AbstractAoeVersion
from aoe_rms_spoon.models import SemanticCheckInput, CodeGenerationInput


class AbstractRMSScriptGenerator(abc.ABC):

    def __init__(self):
        pass

    def create_script(self, file_to_generate: str):
        """
        Main method to use to programmatically call this generator. After this call, you will have a new RMS file just
        generated
        :param aoe_version: the version of age empires you want to target
        :param file_to_generate: output rms file
        """

        # get root instance
        # create AST tree
        root_ast, aoe_version = self._create_script()
        # validate tree (SEMANTIC CHECK)
        semantic_context = SemanticCheckInput(aoe_version)
        semantic_output = root_ast.semantic_validate(semantic_context)
        # list warning
        for i, warning in enumerate(semantic_output.semantic_input.warnings):
            print(f"{i}. {str(warning)}")
        # raise semantic error (if present)
        if not semantic_output.is_correct():
            raise semantic_output.exception

        # generate the RMS file
        generate_input = CodeGenerationInput()
        with open(file_to_generate, mode="w") as rms_file:
            output = root_ast.code_generation(generate_input)
            rms_file.write('\n'.join(output.rms))
            if output.newline:
                rms_file.write("\n")

    @abc.abstractmethod
    def _create_script(self) -> Tuple["AbstractRootRMSStatement", "AbstractAoeVersion"]:
        pass


