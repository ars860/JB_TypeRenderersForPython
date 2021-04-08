from sys import _getframe

import pandas

_builtin_types = {str, int, float, complex, list, tuple, range, dict, set, frozenset, bool, bytes, bytearray,
                  memoryview}


def print_vars():
    parent_frame = _getframe().f_back

    def is_built_in(value):
        return type(value) in _builtin_types

    for k, v in parent_frame.f_locals.items():
        print(k, is_built_in(v))


class MyClass:
    pass


def main():
    a = 123
    b = MyClass()
    c = [1, 2, 3]
    d = pandas.DataFrame()
    print_vars()


if __name__ == '__main__':
    main()
