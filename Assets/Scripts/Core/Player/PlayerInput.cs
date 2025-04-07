using Unity.Netcode;
using UnityEngine;

public class PlayerInput : NetworkBehaviour
{
    [SerializeField]
    private PlayerInput m_PlayerInput;

    public override void OnNetworkSpawn()
    {
        if (!IsOwner) return;

        m_PlayerInput.enabled = true;
    }
}
