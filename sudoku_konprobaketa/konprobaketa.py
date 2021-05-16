import re


def konprobatu(has, sol):
    ondo = True
    # Zutabeak
    for z in range(9):  # Zutabe bakoitzeko
        falta = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
        for e in range(9):  # Errenkada bakoitzeko
            if sol[e][z] not in falta:
                ondo = False
                print("/\tZutabeak txarto")
            else:
                falta.remove(sol[e][z])

    # Errenkadak
    for e in range(9):
        falta = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
        for z in range(9):
            if sol[e][z] not in falta:
                ondo = False
                print("/\tErrenkadak txarto")
            else:
                falta.remove(sol[e][z])

    # Eremuak
    for ee in range(3):
        for ez in range(3):
            falta = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
            for e in range(ee*3, ee*3+3):
                for z in range(ez*3, ez*3+3):
                    if sol[e][z] not in falta:
                        ondo = False
                        print("/\tEremuak txarto")
                    else:
                        falta.remove(sol[e][z])

    # Hasierako balioak soluzioan egotea
    for e in range(9):
        for z in range(9):
            if has[e][z] != "0" and has[e][z] != sol[e][z]:
                ondo = False
                print("/\tHasierako balioak txarto")

    return ondo


def main():
    with open("../res/sudoku.txt") as f:
        lerroak = f.read().splitlines()
        f.close()

    i = 0
    while i < len(lerroak):
        temp = i
        sid = lerroak[temp]
        if re.search("^[Ss][0-9]*$", lerroak[temp]):
            temp += 1
            if re.search("^[0-9]*", lerroak[temp]):
                temp += 1
                hasiera = []
                for z in range(9):
                    hasiera.append(lerroak[temp+z])
                temp += 9
                soluzioa = []
                for z in range(9):
                    soluzioa.append(lerroak[temp + z])
                temp += 9
                ondo = konprobatu(hasiera, soluzioa)
                print("{} sudokua ondo dago: {}".format(sid, ondo))
        i = temp


if __name__ == '__main__':
    main()
