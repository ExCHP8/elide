
<br />

#### Using Elide as a Runtime

Installing Elide on your machine is easy. Pick one of the installation methods below.

<br />

##### One-line installer

> Supported platforms at this time are `darwin-amd64`, `darwin-arm64`, and `linux-amd64`.

The one-line installer is a Bash script which installs the latest CLI version for your machine's architecture. The
script is customizable, and defaults to installing the binary in `~/bin` and updating your `PATH` if needed:

```bash
curl -sSL --tlsv1.2 "dl.elide.dev/cli/install.sh" | bash -s -
```

###### One-liner installer usage

There are several flags which customize the one-line installer. Pass `--help` to see them in your terminal. You should
get something back along the lines of:

```
~ curl -sSL --tlsv1.2 "dl.elide.dev/cli/install.sh" | bash -s - --help

Elide: CLI installer v0.7
https://github.com/elide-dev

Usage:
  ./install.sh
  ./install.sh | bash [options]
  curl https://dl.elide.dev/cli/v1/snapshot/install.sh | bash
  curl https://dl.elide.dev/cli/v1/snapshot/install.sh | bash [options]

Options:
  --install-dir=<path>     Install to a custom directory
  --install-rev=<version>  Install a specific version of Elide
  --[no]-path              Whether to add the install directory to the PATH
  --no-banner              Opt out of the announcement banner after install
  --no-color               Disable color output
  --debug                  Enable debug output
  --trace                  Enable bash tracing
  --version                Show version information
  --help                   Show this help message
```

> Support for Apt, Brew, and other package managers is coming soon.

<br />

##### Usage from NPM/NPX

The Elide CLI is now available via [npm](https://www.npmjs.com/package/@elide-dev/cli).

**Usage from NPX:**
```
npx @elide-dev/elide@alpha --help
```

**Usage from `package.json`:**
```
npm install --save-dev @elide-dev/elide
````
```
yarn add --dev @elide-dev/elide
```
```
pnpm add --save-dev @elide-dev/elide
```
```json
{
  "devDependencies": {
    "@elide-dev/elide": "latest"
  }
}
```

> It is recommended to pin a specific CLI version in your `package.json`.

<br />