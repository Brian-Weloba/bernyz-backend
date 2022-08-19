var path = require("path");

module.exports = {
  entry: "./src/main/js/src/index.js",
  devtool: "eval",
  cache: true,
  mode: "development",
  output: {
    path: __dirname,
    filename: "./src/main/resources/static/built/bundle.js",
  },
//   resolve: {
//     extensions: ["", ".js"],
//     alias: {
//       react: "dummyReact.js",
//     },
//   },
  // externals: {
  //   react: "React",
  //   "react-dom": "ReactDOM",
  // },
 
  module: {
    // noParse: /react/,
    rules: [
      {
        test: path.join(__dirname, "."),
        use: [
          {
            loader: "babel-loader",
            options: {
              presets: ["@babel/preset-env", "@babel/preset-react"],
            },
          },
        ],
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
    ],
  },
};
