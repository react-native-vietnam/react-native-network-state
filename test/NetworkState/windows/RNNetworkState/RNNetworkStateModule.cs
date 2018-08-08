using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Network.State.RNNetworkState
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNNetworkStateModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNNetworkStateModule"/>.
        /// </summary>
        internal RNNetworkStateModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNNetworkState";
            }
        }
    }
}
