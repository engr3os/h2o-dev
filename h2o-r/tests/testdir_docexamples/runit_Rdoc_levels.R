setwd(normalizePath(dirname(R.utils::commandArgs(asValues=TRUE)$"f")))
source('../h2o-runit.R')

test.levels.golden <- function(H2Oserver) {
	

irisPath <- system.file("extdata", "iris.csv", package="h2o")
iris.hex <- h2o.importFile(H2Oserver, path = irisPath, key = "iris.hex")
levels(iris.hex[,5])


testEnd()
}

doTest("R Doc levels", test.levels.golden)
