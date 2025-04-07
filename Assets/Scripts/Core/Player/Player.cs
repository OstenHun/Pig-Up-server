using Unity.Netcode;
using UnityEngine;
using UnityEngine.InputSystem;

namespace MSE.Core
{
    public class Player : NetworkBehaviour
    {

        public override void OnNetworkSpawn()
        {
            Debug.Log($"IsOwner: {IsOwner}");
        }
    }

}
